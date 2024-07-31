import { useContext } from 'react';
import { AuthContext, AuthContextType } from '../context/AuthProvider';

const useHasRole = (requiredRole: string) => {
  const { roles } = useContext<AuthContextType>(AuthContext);
  
  const hasRole = roles?.includes(requiredRole);

  return hasRole;
};

export default useHasRole;
