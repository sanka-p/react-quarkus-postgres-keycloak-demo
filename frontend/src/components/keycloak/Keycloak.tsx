import {initAuth, getAccessToken, getRoles, isLoggedIn} from "../../auth/keycloak";
import { useEffect, useContext } from "react";
import { AuthContext, AuthContextType } from "../../context/AuthProvider";
import Crash from "../../pages/errors/Crash";
import App from "../../App";

export function KeycloakWrapper()  {
    const {setAccessToken, setRoles} = useContext<AuthContextType>(AuthContext)

    const onAuthSuccessCallback = () => {
        setAccessToken(getAccessToken());
        setRoles(getRoles());
    }

    const onAuthErrCallback = () => {
        window.location.reload();
    }

    const onErrorCallback = (err: unknown) => {
        console.error(err);
        return <Crash />
    }

    // Initialize keycloak at mount
    useEffect(()=>{
        initAuth(
            onAuthSuccessCallback,
            onAuthErrCallback,
            onErrorCallback
        );
    }, [])

    return isLoggedIn() ? <App /> : null; // TODO retry login
}

export default KeycloakWrapper;