import tripApi from '../../api/tripApi';
import { Trip } from '../../types/trip';
import { Link } from 'react-router-dom';    
import React, { useEffect, useState } from 'react';
import Navbar from '../../components/Navbar';

const AllTripsPage = () => {
    const [trips, setTrips] = useState<Trip[]>([]); // Initialize trips state
    const userId = 1;

    useEffect(() => {
        // Fetch trips data when the component mounts
        const fetchTrips = async () => {
            const fetchedTrips = await tripApi.getTrips(userId);
            setTrips(fetchedTrips);
        };

        fetchTrips();
    }, [userId]); // Dependency array: re-run effect if userId changes

    return (
        <div>
            <Navbar />
            <div className='px-5 py-2 my-5 border-4 border-indigo-500/100 mx-2 max-w-4xl'>
                <h1 className='text-3xl font-semibold mt-6'>All Trips</h1>
                <Link to='/trip/create' className='text-blue-700 hover:underline'>Create a new trip</Link>
                <div className='mt-4'>
                    {trips.map((trip) => (
                        <div key={trip.id} className='border-b border-gray-200 dark:border-gray-600 py-4'>
                            <h2 className='text-xl font-semibold'>{trip.name}</h2>
                            <p className='text-gray-500'>{trip.destination}</p>
                            <p className='text-gray-500'>{trip.startDate} - {trip.endDate}</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default AllTripsPage;