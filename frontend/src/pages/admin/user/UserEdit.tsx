import { useEffect, useState } from 'react';
import { User } from "../../../types/admin/user/UserTypes";
import UserForm from "./UserForm";
import { Button, Container } from 'react-bootstrap';
import { AxiosResponse } from 'axios';
import axios from '../../../api/axios';
import { useNavigate, useParams } from 'react-router-dom';
import Spinner from "react-bootstrap/Spinner";
const USER_URL = "/users"
import ButtonGroup from 'react-bootstrap/ButtonGroup';
type Mode = "View" | "Edit";

function UserEdit() {
    const [initialFormData, setInitialFormData] = useState<User>({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        dateOfBirth: ""
    });

    const {userId} = useParams();

    const [formState, setFormState] = useState<Mode>("View");

    const [formData, setFormData] = useState<User>(initialFormData);
    
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const [isError, setIsError] = useState<boolean>(false);

    const resetForm = () => {
        setFormData(initialFormData);
    }

    const fetchUser = async () => {
        try {
            setIsLoading(true);
            const response: AxiosResponse = await axios.get(`${USER_URL}/${userId}`);
            setInitialFormData(response.data)
            setFormData(response.data);      
        } catch(err) {
            console.log(err);
            setIsError(true);
        }  finally {
          setIsLoading(false);
        }
    }

    useEffect(()=>{
        fetchUser();
    }, [])

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setFormData((previousData) => ({
            ...previousData,
            [name]: value,
        }));
    };

    const [isSubmitting, setIsSubmitting] = useState<boolean>(false);

    const navigate = useNavigate(); 

    const handlePut = async () => {
        try {
            setIsSubmitting(true);
            await axios.put(`${USER_URL}/${userId}`, formData);
            navigate("../")           
        } catch(err) {
            console.log(err);
            setIsError(true);
        }  finally {
            setIsSubmitting(false);
        }
    }

    const handleDelete = async () => {
        try {
            setIsSubmitting(true);
            await axios.delete(`${USER_URL}/${userId}`);
            navigate("../")           
        } catch(err) {
            console.log(err);
            setIsError(true);
        }  finally {
            setIsSubmitting(false);
        }
    }

    if (isLoading) return (
        <Spinner animation="border" role="status"></Spinner>
    );

    if (isError) return (
        <div>Something went wrong</div>
    );

    return (<>
        <UserForm formData={formData} handleChange={handleChange} isDisabled={formState == "View"}/>
        <Container>
        {
            formState == "View" ? (
                <ButtonGroup>
                    <Button variant="primary" onClick={()=>{setFormState("Edit")}}>Edit</Button>
                    <Button variant="danger" onClick={handleDelete} disabled={isSubmitting}>Delete</Button>
                </ButtonGroup>
            ) : (
                <ButtonGroup>
                    <Button 
                        variant="primary"
                        onClick={handlePut} 
                        disabled={isSubmitting} 
                    >
                        Update
                    </Button>
                    <Button variant="warning" onClick={()=>{
                            setFormState("View");
                            resetForm();
                        }}>Cancel</Button>
                </ButtonGroup>
            )
        }
        </Container>
    </>);
}

export default UserEdit;