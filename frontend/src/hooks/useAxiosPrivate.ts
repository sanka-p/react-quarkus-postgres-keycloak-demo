import { useContext } from 'react';
import { AuthContext, AuthContextType } from '../context/AuthProvider';
import axios from '../api/axios';

const useAxiosPrivate = () => {
  const { accessToken } = useContext<AuthContextType>(AuthContext);
  
    // Add a request interceptor
    axios.interceptors.request.use(function (config) {
        config.headers.Authorization =  `Bearer ${accessToken}`;
        return config;
    }, function (error) {
        // Do something with request error
        return Promise.reject(error);
    });

  return axios;
};

export default useAxiosPrivate;