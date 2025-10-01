import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL, // <-- dynamically set from Vercel
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
