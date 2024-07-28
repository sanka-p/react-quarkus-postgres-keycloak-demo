import client from "src/api/axios";
import { AxiosResponse } from "axios";
import User from "src/api/types/admin/user/userTypes"
import { useEffect, useState } from "react";

const USER_URL = "/users"

const UserManagement = () => {
    const [users, setUsers] = useState<User[]>([]);

    const fetchUsers = async () => {

        try {
            const response: AxiosResponse = await client.get(USER_URL);
            setUsers(response.data)   
            console.log("Logging: ", response.data)
        } catch(err) {
            console.log(err);
        }  
    }

    useEffect(()=>{fetchUsers()}, []);

    return (
        <div>
          <h1>My List</h1>
          <ul>
            {users.map((user, index) => (
              <li key={index}>{user.firstName}</li>
            ))}
          </ul>
        </div>
      );

}

export default UserManagement;