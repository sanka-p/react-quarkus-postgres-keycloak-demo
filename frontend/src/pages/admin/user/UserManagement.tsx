import client from "../../../api/axios";
import { AxiosResponse } from "axios";
import { User } from "../../../types/admin/user/UserTypes"
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Spinner from "react-bootstrap/Spinner";
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const USER_URL = "/users"

const UserManagement = () => {
    const [users, setUsers] = useState<User[] | null>([]);

    const [isLoading, setIsLoading] = useState<boolean>(false);

    const [isError, setIsError] = useState<boolean>(false);

    const fetchUsers = async () => {
        try {
            setIsLoading(true);
            const response: AxiosResponse = await client.get(USER_URL);
            setUsers(response.data)             
        } catch(err) {
            console.log(err);
            setIsError(true);
        }  finally {
          setIsLoading(false);
        }
    }

    // Load user list at mount
    useEffect(()=>{fetchUsers()}, []);

    const handleClick = (e) => {console.log(e)}

    const navigate = useNavigate(); 
    const routeChange = () =>{ 
      navigate("./new");
    }


    if (isLoading) return (
      <Spinner animation="border" role="status"></Spinner>
    );

    if (isError) return (
      <div>Something went wrong</div>
    );

    return (
        <>
          <Container>
            <Button variant="primary" onClick={routeChange}>Create</Button>
          </Container>
          <Container>
          <Row>
            <Col>First Name</Col>
            <Col>Last Name</Col>
            <Col>Email</Col>
            <Col>Number</Col>
            <Col>DoB</Col>
          </Row>
          {users?.map((user, index) => (
              <Row onClick={handleClick}>
                <Col key={index}>{user.firstName}</Col>
                <Col key={index}>{user.lastName}</Col>
                <Col key={index}>{user.email}</Col>
                <Col key={index}>{user.phoneNumber}</Col>
                <Col key={index}>{user.dateOfBirth}</Col>
              </Row>
            ))}
        </Container>
        </>
    );
}

export default UserManagement;