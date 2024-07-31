import { useState } from 'react';
import { User } from "../../../types/admin/user/UserTypes";
import UserForm from "./UserForm";
import { Button, Container } from 'react-bootstrap';
import { AxiosResponse } from 'axios';
import axios from '../../../api/axios';
import { useNavigate } from 'react-router-dom';
const USER_URL = "/users"

function UserAdd() {
    const initialFormData: User = {
        firstName: "test",
        lastName: "test",
        email: "test@example.com",
        phoneNumber: "0123456789",
        dateOfBirth: "11/09/2001"
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

    const [isSubmitting, setIsSubmitting] = useState<boolean>(false);

    const [isError, setIsError] = useState<boolean>(false);

    const navigate = useNavigate(); 

    const handlePost = async () => {
        try {
            setIsSubmitting(true);
            await axios.post(USER_URL, formData);
            navigate("../")           
        } catch(err) {
            console.log(err);
            setIsError(true);
        }  finally {
            setIsSubmitting(false);
        }
    }

    if (isError) 
        return <>Something went wrong</>

    return (<>
        <UserForm formData={formData} handleChange={handleChange} isDisabled={false}/>
        <Container>
            <Button onClick={handlePost} disabled={isSubmitting}>Submit</Button>
        </Container>
    </>);
}

export default UserAdd;