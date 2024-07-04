import React, { useState } from 'react';
import tripApi from '../../api/tripApi';
import { Trip } from '../../types/trip';
import Navbar from '../../components/Navbar';

const CreateTripPage = () => {

    // create a typescript interface for the trip and create a state variable for the trip
    const [trip, setTrip] = useState<Trip>({
        id: '',
        name: '',
        destination: '',
        coverPhotoUrl: '',
        startDate: '',
        endDate: '',
        userId: '1',
    });
    // create a function to handle the form submission
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await tripApi.createTrip(trip);
            console.log(response);
        } catch (error) {
            console.error(error);
    
        }
    };
    // create a function to handle the form change
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTrip({
            ...trip,
            [e.target.name]: e.target.value,
        });
    };
    // create a function to handle the file change
    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setTrip({
                    ...trip,
                    coverPhotoUrl: reader.result as string,
                });
            };
            reader.readAsDataURL(file);
        }
    };
    return (
        <div className='flex flex-col h-screen'>
            <div>
                <Navbar />
            </div>
            <div className="px-5 py-2 my-5 border-4 border-indigo-500/100 mx-2 max-w-4xl">
                <h1 className="text-3xl font-semibold mt-6">Create Trip</h1>
                <form onSubmit={handleSubmit} className="mt-4">
                    <div className="flex flex-col space-y-4">
                        <input
                            type="text"
                            name="name"
                            value={trip.name}
                            onChange={handleChange}
                            placeholder="Name"
                            className="p-2 border-2 border-indigo-500/100"
                        />
                        <input
                            type="text"
                            name="destination"
                            value={trip.destination}
                            onChange={handleChange}
                            placeholder="Destination"
                            className="p-2 border-2 border-indigo-500/100"
                        />
                        <input
                            type="date"
                            name="startDate"
                            value={trip.startDate}
                            onChange={handleChange}
                            placeholder="Start Date"
                            className="p-2 border-2 border-indigo-500/100"
                        />
                        <input
                            type="date"
                            name="endDate"
                            value={trip.endDate}
                            onChange={handleChange}
                            placeholder="End Date"
                            className="p-2 border-2 border-indigo-500/100"
                        />
                        <input
                            type="file"
                            onChange={handleFileChange}
                            className="p-2 border-2 border-indigo-500/100"
                        />
                        <button type="submit" className="bg-indigo-500 text-white p-2 rounded-md">
                            Create Trip
                        </button>
                    </div>
                </form>
            </div>
        </div>
    ); 
};
export default CreateTripPage;