import { useState } from 'react';
import { User } from "../../../types/admin/user/UserTypes";
import UserForm from "./UserForm";

function UserAdd() {
    const initialFormData: User = {
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        dateOfBirth: ""
    };

    const [formData, setFormData] = useState<User>(initialFormData);
    
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setFormData((previousData) => ({
            ...previousData,
            [name]: value,
        }));
    };

    return <UserForm formData={formData} handleChange={handleChange} isDisabled={true}/>
}

export default UserAdd;