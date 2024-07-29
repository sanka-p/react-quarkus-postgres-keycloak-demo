import UserManagement from "./pages/admin/user/UserManagement";
import UserAdd from "./pages/admin/user/UserAdd";
import UserEdit from "./pages/admin/user/UserEdit";
import { Route, Routes } from "react-router-dom";
import Keycloak from "./auth/Keycloak";
import Dashboard from "./pages/dashboard/Dashboard";

function Router() {
    return (
        <Routes>
            <Route path="/">
            {/* <Route path="/" element={<Keycloak />}> */}
                <Route index element={<Dashboard />} />
                <Route path="admin/">
                    <Route path="users/">
                        <Route index element={<UserManagement />}/>
                        <Route path="new" element={<UserAdd/>}/>
                        <Route path=":userId" element={<UserEdit/>}/>
                    </Route>
                </Route>
            </Route>
        </Routes>
    );
}

export default Router;