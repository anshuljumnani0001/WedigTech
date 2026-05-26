import { useState } from 'react';
import './App.css';

function App() {
  const [url, setUrl] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [history, setHistory] = useState(
    JSON.parse(localStorage.getItem('history') || '[]')
  );

  const handleSubmit = async () => {
    setError('');
    setResult(null);
    if (!url.trim()) { setError('Please enter a URL'); return; }

    setLoading(true);
    try {
      const res = await fetch('http://localhost:8080/api/shorten', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ url })
      });
      const data = await res.json();
      if (!res.ok) { setError(data.error); return; }

      setResult(data.shortUrl);
      const newHistory = [{ original: url, short: data.shortUrl }, ...history].slice(0, 5);
      setHistory(newHistory);
      localStorage.setItem('history', JSON.stringify(newHistory));
      setUrl('');
    } catch {
      setError('Server error. Is backend running?');
    } finally {
      setLoading(false);
    }
  };

  const copyToClipboard = (text) => {
    navigator.clipboard.writeText(text);
    alert('Copied!');
  };

  return (
    <div className="container">
      <h1>URL Shortener</h1>
      <p>Paste a long URL and get a short link instantly</p>

      <div className="input-row">
        <input
          type="text"
          placeholder="https://example.com/very/long/url"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          onKeyDown={(e) => e.key === 'Enter' && handleSubmit()}
        />
        <button onClick={handleSubmit} disabled={loading}>
          {loading ? 'Shortening...' : 'Shorten'}
        </button>
      </div>

      {error && <p className="error">{error}</p>}

      {result && (
        <div className="result">
          <span>{result}</span>
          <button onClick={() => copyToClipboard(result)}>Copy</button>
        </div>
      )}

      {history.length > 0 && (
        <div className="history">
          <h3>Recent URLs</h3>
          {history.map((item, i) => (
            <div className="history-item" key={i}>
              <span className="original">{item.original.slice(0, 40)}...</span>
              <a href={item.short} target="_blank" rel="noreferrer">{item.short}</a>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default App;