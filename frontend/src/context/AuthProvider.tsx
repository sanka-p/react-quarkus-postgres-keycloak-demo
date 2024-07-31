import { createContext, useState } from "react";

export interface AuthContextType {
    accessToken: string | undefined;
    setAccessToken: (previousToken: string | undefined) => void;
    roles: string[] | undefined;
    setRoles: (previousRoles: string[] | undefined) => void;
}

interface AuthProviderProps {
    children: React.ReactNode
}

export const AuthContext = createContext<AuthContextType>({
    accessToken: undefined,
    setAccessToken: () => {},
    roles: [],
    setRoles: () => {}
})

export function AuthProvider({ children }: AuthProviderProps ) {
    const [accessToken, setAccessToken] = useState<string|undefined>(undefined)
    const [roles, setRoles] = useState<string[]|undefined>([]);

    return (
        <AuthContext.Provider
            value={{
                accessToken,
                setAccessToken,
                roles,
                setRoles
            }}
        >
            {children}
        </AuthContext.Provider>
    )

}