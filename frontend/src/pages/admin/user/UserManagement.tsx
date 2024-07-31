import client from "../../../api/axios";
import { AxiosResponse } from "axios";
import { User } from "../../../types/admin/user/UserTypes"
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useHasRole from "../../../hooks/useHasRole";
import useAxiosPrivate from "../../../hooks/useAxiosPrivate";
import Spinner from "react-bootstrap/Spinner";
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const USER_URL = "/users"

const UserManagement = () => {
    const axiosPrivate = useAxiosPrivate();

    const hasRole = useHasRole("manager");

    const [users, setUsers] = useState<User[]>([]);

    const [isLoading, setIsLoading] = useState<boolean>(false);

    const [isError, setIsError] = useState<boolean>(false);

    const fetchUsers = async () => {
        try {
            setIsLoading(true);
            const response: AxiosResponse = await axiosPrivate.get(USER_URL);
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

    const handleClick = (userId:number | undefined) => {
      if (userId == undefined) return;
      navigate(`./${userId}`);
    };

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

    if (!hasRole)
      return <div>Unauthorized</div>

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
              <Row onClick={() => handleClick(user.id)}>
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