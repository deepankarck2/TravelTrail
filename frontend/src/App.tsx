import React, { useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';

function App() {

  const [message, setMessage] = useState("");


  useEffect(() => {
    // Fetch data from the backend
    axios.get('http://localhost:8080')
      .then(response => {
        console.log(response.data);
        setMessage(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the data!', error);
      });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>The message is below:</h1>
        <p>{message}</p>
      </header>
    </div>
  );
}

export default App;
