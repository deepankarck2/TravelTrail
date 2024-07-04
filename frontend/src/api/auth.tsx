import axios from 'axios';

type User = {
    password: string;
    email: string;
};

export const fetchHelloMessage = () => {
  return axios.get('http://localhost:8080/api/public')
    .then(response => response.data)
    .catch(error => {
      console.error('There was an error fetching the data!', error);
      throw error;
    });
};


export const login = (username: string, password: string) => {
    return axios.post('http://localhost:8080/api/v1/auth/login', {
        username,
        password
    })
        .then(response => response.data)
        .catch(error => {
        console.error('There was an error logging in!', error);
        throw error;
        });
    };

export const register = (user: User) => {
    return axios.post('http://localhost:8080/api/v1/auth/register', {
        email: user.email,
        password: user.password
    })
        .then(response => response.data)
        .catch(error => {
        console.error('There was an error registering!', error);
        throw error;
        });
    };
