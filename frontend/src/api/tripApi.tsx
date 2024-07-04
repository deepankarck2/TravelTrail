import { Trip } from '../types/trip';

const API_URL: string = 'http://localhost:8080/api/trips';

const tripApi = {
    
    async getTrips(userId: number): Promise<Trip[]> {
        const response = await fetch(`${API_URL}/${userId}`);
        return await response.json();
    },
    async createTrip(trip: Trip): Promise<Trip> {
        const response = await fetch(`${API_URL}/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(trip),
        });
        return await response.json();
    },
};

export default tripApi;
