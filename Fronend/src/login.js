import React, { useState } from "react";
import { Link, withRouter } from "react-router-dom";
import { Form, Button, Container, Grid, Divider } from "semantic-ui-react";
import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import { LinkUtil } from "./util";

const Login = ({ useStateWithLocalStorage }) => {
  const [usernamePassword, setUsernamePassword] = useState({
    username: "",
    password: "",
  });
  const [usernamePasswordError, setUsernamePasswordError] = useState({
    username: null,
    password: null,
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setUsernamePassword({ ...usernamePassword, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const { username, password } = usernamePassword;
    if (username.length > 10) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        username: "Please enter an email with character less than 3!",
      });
      return;
    }

    if (password.length < 3) {
      setUsernamePasswordError({
        password: "Please enter an password with characters mare than 3!",
      });
      return;
    }
    fetch("http://localhost:8080/api/auth/signin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    })
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Bir hata oluştu"));
        }
        return Promise.reject(new Error("Bilinmeyen hata oluştu"));
      })
      .then((r) => r.json())
      .then((response) => {
        toast.success(`User with id ${response.id} logged in!`);

        localStorage.setItem("token", response.token);
        localStorage.setItem("id", response.id);

        localStorage.setItem("role", response.roles[0]);
        toast.success(`Redirecting to dashboard page...`);
      })
      .catch((e) => {
        toast.error(e.message);
      });

    //jwt.decode
    //alert("Form has been submitted");
  };

  return (
    <Container>
      <Grid columns="equal">
        <Grid.Column></Grid.Column>

        <Grid.Column>
          <Form onSubmit={handleSubmit}>
            <Form.Field>
              <label>UserName</label>
              <Form.Input
                type="text"
                name="username"
                error={usernamePasswordError.username}
                required
                value={usernamePassword.username}
                onChange={handleChange}
              ></Form.Input>
            </Form.Field>
            <Form.Field>
              <label>Password</label>
              <Form.Input
                type="password"
                name="password"
                error={usernamePasswordError.password}
                required
                value={usernamePassword.password}
                onChange={(event) => {
                  handleChange(event);
                }}
              ></Form.Input>
            </Form.Field>

            <Button type="submit">Submit</Button>
          </Form>
          <Divider />
          <Link to="/">You don't have an account? Please Register- </Link>
          <LinkUtil></LinkUtil>
        </Grid.Column>

        <Grid.Column></Grid.Column>
      </Grid>
    </Container>
  );
};
export default withRouter(Login);
