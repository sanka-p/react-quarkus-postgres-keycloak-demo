import Keycloak from "keycloak-js";

const initOptions = {
 url: import.meta.env.VITE_KC_BASE_URL,
 realm: import.meta.env.VITE_KC_REALM,
 clientId: import.meta.env.VITE_KC_CLIENT_ID
};

const _kc = new Keycloak(initOptions);

const initAuth = async (
        onAuthSuccessCallback: () => void, 
        onAuthErrorCallback: () => void, 
        onErrCallback: (err: unknown) => void) => {
    try {
        const authenticated = await _kc.init({
            onLoad: "login-required",
            checkLoginIframe: false,
            redirectUri: window.location.href,
        })

        if (!authenticated) onAuthErrorCallback();
        else onAuthSuccessCallback();

    } catch (err) {
        onErrCallback(err);
    }
}

const getAccessToken : () => string | undefined = () => _kc.token;

const getRoles : () => string[] | undefined = () => _kc.realmAccess?.roles;

const doLogout = () => _kc.logout;

const isLoggedIn = () => !!_kc.token;

export {
    initAuth,
    getAccessToken,
    getRoles,
    doLogout,
    isLoggedIn
}