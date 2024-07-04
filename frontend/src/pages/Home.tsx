import { useEffect, useState } from 'react';
import { fetchHelloMessage } from '../api/auth';
import Navbar from '../components/Navbar';

const Home = () => {
  // create a typescript interface for the message and create a state variable for the message
  const [message, setMessage] = useState<string>('');

  useEffect(() => {
    fetchHelloMessage()
      .then(data => {
        console.log(data);
        setMessage(data);
      })
      .catch(error => {
        console.error('There was an error fetching the data!', error);
      });
  }, []);

  return (
    <div className='flex flex-col h-screen'>
      <div>
        <Navbar />
      </div>
      <div className="px-5 my-5 border-4 border-indigo-500/100 mx-2 max-w-4xl">
        <h1 className="text-3xl font-semibold mt-8">Home</h1>
        <p className="mt-4">This is the home page. The message from the server is: {message}</p>
      </div>
    </div>
  );
}

export default Home;