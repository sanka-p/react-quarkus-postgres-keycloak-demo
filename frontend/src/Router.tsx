import { Route, Routes } from "react-router-dom";
import Keycloak from "src/auth/Keycloak";

function Router() {
    return (
        <Routes>
            <Route path="/" Element={<Keycloak />}>
                <Route index Element={<Dashboard />} />
            </Route>
        </Routes>
    );
}

export default Router;