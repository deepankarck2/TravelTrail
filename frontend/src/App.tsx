import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Register from './pages/Register';
import Login from './pages/Login';
import CreateTripPage from './pages/authPages/CreateTripPage';
import AllTripsPage from './pages/authPages/AllTripsPage';

function App() {


  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<h1>Not Found 404</h1>} />
          <Route path="/trip/create" element={<CreateTripPage />} />
          <Route path='/trip/trips' element={<AllTripsPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
