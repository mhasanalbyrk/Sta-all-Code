import React from "react";
import { Link, withRouter } from "react-router-dom";
import { toast } from "react-toastify";
import { Form, Button, Container, Grid, Divider } from "semantic-ui-react";
import fetch from "isomorphic-unfetch";

class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      passwordRepeat: "",
      email: "",
      role: [],
    };
  }
  handleSubmit = (event) => {
    event.preventDefault();
    const { username, password, passwordRepeat, email, role } = this.state;
    if (username.length > 10) {
      this.setState({
        usernameError: "Please enter an username with characters less than 10!",
      });
      return;
    }

    if (password.length < 3) {
      this.setState({
        passwordError: "Please enter an password with characters mare than 6!",
      });
      return;
    }

    if (password !== passwordRepeat) {
      this.setState({
        passwordError: "Please make sure that password match",
        passwordRepeatError: "Please make sure that password match",
      });
      return;
    } else {
      this.setState({
        passwordError: null,
        passwordRepeatError: null,
        usernameError: null,
      });
    }

    fetch("http://localhost:8080/api/auth/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password, email, role }),
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
        toast.success(`User with id ${response.id} added`);
        toast.success(`Redirecting to login page...`);
        setTimeout(() => {
          this.props.history.push("/login");
        }, 3000);
      })
      .catch((e) => {
        toast.error(e.message);
      });

    //alert("Form has been submitted");
  };
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  render() {
    return (
      <Container>
        <Grid columns="equal">
          <Grid.Column></Grid.Column>

          <Grid.Column>
            <Form onSubmit={this.handleSubmit}>
              <Form.Field>
                <label>UserName</label>
                <Form.Input
                  type="text"
                  name="username"
                  error={this.state.usernameError}
                  required
                  value={this.state.username}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Email</label>
                <Form.Input
                  type="email"
                  name="email"
                  error={this.state.usernameError}
                  required
                  value={this.state.email}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Password</label>
                <Form.Input
                  type="password"
                  name="password"
                  error={this.state.passwordError}
                  required
                  value={this.state.password}
                  onChange={(event) => {
                    this.handleChange(event);
                  }}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Password Again</label>
                <Form.Input
                  type="password"
                  name="passwordRepeat"
                  error={this.state.passwordRepeatError}
                  required
                  value={this.state.passwordRepeat}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Role</label>
                <Form.Input
                  type="text"
                  name="role"
                  error={this.state.passwordRepeatError}
                  required
                  value={this.state.role}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>

              <Button type="submit">Submit</Button>
            </Form>
            <Divider />
            <Link to="/login">Do you have an account? Please login</Link>
          </Grid.Column>

          <Grid.Column></Grid.Column>
        </Grid>
      </Container>
    );
  }
}
export default withRouter(Register);
