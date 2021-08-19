import React from "react";
import { toast } from "react-toastify";
import fetch from "isomorphic-unfetch";
import { Link, withRouter } from "react-router-dom";
import {
  Form,
  Button,
  Container,
  Grid,
  Divider,
  GridColumn,
  Segment,
} from "semantic-ui-react";
import { Table, Menu, Icon, Label } from "semantic-ui-react";
import { AddFavUtil, MenuUtil, getUsersUtil, blacListUtil } from "./util";

class AdminDashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      users: {},
      currentPage: 0,
      favCurrentPage: 0,
      blackListCurrentPage: 0,
      favorites: {},
      favName: null,
      blackList: {},
      blackListUsername: null,
      editUserName: null,
      editEmail: null,
      newUserName: null,
      newEmail: null,
      newPassword: null,
      name: null,
      email: null,
      editing: false,
      userId: null,
    };
  }
  componentDidMount = () => {
    this.getUsers();
    /*getUsersUtil(currentPage, name, email).then((response) => {
      this.setState({
        users: response,
      });
    });*/
  };
  /*componentDidMount = () => {
    const { name, description, category, currentPage } = this.state;
    //this.getProducts();
    getProductsUtil(name, description, category, currentPage).then(
      (response) => {
        this.setState({
          products: response,
        });
      }
    );
  };*/

  blackList(sellerId) {
    blacListUtil(sellerId, "remove");
  }

  favList = (productId) => {
    const userId = this.state.userId;
    AddFavUtil(productId, "remove", userId);
  };

  editUser = (userId) => {
    const { users } = this.state;
    this.setState({ editing: true });
    this.setState({ userId: userId });
    users.content.forEach((element) => {
      if (element.id === userId) {
        this.setState({
          favorites: element.favoriteList,
        });
        this.setState({
          blackList: element.blackList,
        });
      }
    });
  };

  deleteUser = (userId) => {
    const token = localStorage.getItem("token");
    fetch(
      "http://localhost:8080/api/user/delete?" +
        new URLSearchParams({
          id: userId,
        }),

      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        credentials: "include",
      }
    )
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Bir hata oluştu"));
        }
        return Promise.reject(new Error("Bilinmeyen bir hata oluştu."));
      })
      .then((r) => r.json())
      .then((response) => {
        this.setState({ users: response });
        toast.info(`${response.totalElements} fetched succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  addUser = (event) => {
    event.preventDefault();
    const { newUserName, newEmail, newPassword } = this.state;

    if (newUserName.length > 10) {
      this.setState({
        usernameError: "Please enter an username with characters less than 10!",
      });
      return;
    }

    if (newPassword.length < 6) {
      this.setState({
        passwordError: "Please enter an password with characters mare than 6!",
      });
      return;
    }
    const role = "user";

    fetch("http://localhost:8080/api/auth/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: newUserName,
        password: newPassword,
        email: newEmail,
        role,
      }),
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
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  getUsers = () => {
    const token = localStorage.getItem("token");

    /** getProductsUtil(name, description, category, currentPage).then(
      (response) => {
        this.setState({
          products: response,
        });
      }
    ); */

    fetch(
      "http://localhost:8080/api/user/all?" +
        new URLSearchParams({
          pageNumber: this.state.currentPage,
          name: this.state.name,
          email: this.state.email,
          //description: this.state.description,
        }),

      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        credentials: "include",
      }
    )
      .then((r) => {
        if (r.ok) {
          return r;
        }
        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("Bir hata oluştu"));
        }
        return Promise.reject(new Error("Bilinmeyen bir hata oluştu."));
      })
      .then((r) => r.json())
      .then((response) => {
        this.setState({ users: response });
        toast.info(`${response.totalElements} fetched succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
    this.setState({ name: null });
    this.setState({ category: null });
    this.setState({ description: null });
  };

  changePageTo = (i) => {
    this.setState({ currentPage: i }, this.getUsers);
  };
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  render() {
    const { users } = this.state;
    const { favorites } = this.state;
    const { blackList } = this.state;
    const { editing } = this.state;
    const pageArray = [...Array(users.totalPages).keys()];
    return (
      <Container>
        <MenuUtil></MenuUtil>
        <Form onSubmit={this.addUser}>
          <Form.Group>
            <Form.Field>
              <label>UserName</label>
              <Form.Input
                type="text"
                required
                name="newUserName"
                value={this.state.newUserName}
                onChange={this.handleChange}
              ></Form.Input>
            </Form.Field>
            <Form.Field>
              <label>Email</label>
              <Form.Input
                type="email"
                required
                name="newEmail"
                value={this.state.newEmail}
                onChange={this.handleChange}
              ></Form.Input>
            </Form.Field>

            <Form.Field>
              <label>Password</label>
              <Form.Input
                type="text"
                required
                name="newPassword"
                value={this.state.newPassword}
                onChange={this.handleChange}
              ></Form.Input>
            </Form.Field>

            <Button type="submit">Add New User</Button>
          </Form.Group>
        </Form>
        <Divider />
        <Grid>
          <Grid.Column width={4}>
            <Form onSubmit={this.getUsers} name={"userSearch"}>
              <Form.Field>
                <label>UserName</label>
                <Form.Input
                  type="text"
                  name="name"
                  value={this.state.name}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Email</label>
                <Form.Input
                  type="text"
                  name="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>

              <Button type="submit">Search</Button>
            </Form>
          </Grid.Column>

          <Grid.Column>
            <Table celled selectable sortable striped>
              <Table.Header>
                <Table.Row>
                  <Table.HeaderCell>Number</Table.HeaderCell>
                  <Table.HeaderCell>ID</Table.HeaderCell>
                  <Table.HeaderCell>UserName</Table.HeaderCell>
                  <Table.HeaderCell>Email</Table.HeaderCell>
                  <Table.HeaderCell>Edit</Table.HeaderCell>
                  <Table.HeaderCell>Delete</Table.HeaderCell>
                </Table.Row>
              </Table.Header>
              <Table.Body>
                {users &&
                  users.content &&
                  users.content.map((value, index) => {
                    return (
                      <Table.Row>
                        <Table.Cell>
                          <Label ribbon>
                            {users.size * users.number + (index + 1)}
                          </Label>
                        </Table.Cell>
                        <Table.Cell>
                          <Icon name="barcode"></Icon>
                          {value.id}
                        </Table.Cell>
                        <Table.Cell>{value.name}</Table.Cell>
                        <Table.Cell>{value.email}</Table.Cell>
                        <Table.Cell
                          positive
                          a
                          lkey={value.id}
                          onClick={() => this.editUser(value.id)}
                        >
                          <Button>Edit</Button>
                        </Table.Cell>
                        <Table.Cell
                          negative
                          a
                          lkey={value.id}
                          onClick={() => this.deleteUser(value.id)}
                        >
                          <Button>Delete</Button>
                        </Table.Cell>
                      </Table.Row>
                    );
                  })}
              </Table.Body>

              <Table.Footer>
                <Table.Row>
                  <Table.HeaderCell colSpan="4">
                    <Menu floated="right" pagination>
                      <Menu.Item
                        onClick={() => {
                          this.changePageTo(this.state.currentPage - 1);
                        }}
                        as="a"
                        disabled={users.first}
                        icon
                      >
                        <Icon name="chevron left" />
                      </Menu.Item>
                      {pageArray.map((value, index) => {
                        return (
                          <Menu.Item
                            onClick={() => {
                              this.changePageTo(index);
                            }}
                            active={users.number === value}
                            as="a"
                          >
                            {value + 1}
                          </Menu.Item>
                        );
                      })}
                      <Menu.Item
                        onClick={() => {
                          this.changePageTo(this.state.currentPage + 1);
                        }}
                        as="a"
                        disabled={users.last}
                        icon
                      >
                        <Icon name="chevron right" />
                      </Menu.Item>
                    </Menu>
                  </Table.HeaderCell>
                </Table.Row>
              </Table.Footer>
            </Table>
          </Grid.Column>
        </Grid>
        <Divider />
        <Segment disabled={!editing}>
          <Grid>
            <GridColumn width={3}>
              <Form
                onSubmit={this.updateUser}
                onScreen={editing}
                name={"Edit user form"}
              >
                <Form.Field>
                  <label>New Username</label>
                  <Form.Input
                    type="text"
                    name="editUserName"
                    value={this.state.editUserName}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>New Email</label>
                  <Form.Input
                    type="text"
                    name="editEmail"
                    value={this.state.editEmail}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>

                <Button type="submit">Edit</Button>
              </Form>
            </GridColumn>
            <GridColumn width={4}>
              <Table celled selectable sortable striped>
                <Table.Header>
                  <Table.Row>
                    <Table.HeaderCell>ID</Table.HeaderCell>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Category</Table.HeaderCell>
                    <Table.HeaderCell>Remove</Table.HeaderCell>
                  </Table.Row>
                </Table.Header>
                <Table.Body>
                  {favorites &&
                    favorites.productFavList &&
                    favorites.productFavList.map((value, index) => {
                      return (
                        <Table.Row>
                          <Table.Cell>
                            <Icon name="barcode"></Icon>
                            {value.id}
                          </Table.Cell>
                          <Table.Cell>{value.name}</Table.Cell>
                          <Table.Cell>{value.category}</Table.Cell>
                          <Table.Cell
                            negative
                            a
                            lkey={value.id}
                            onClick={() => this.favList(value.id)}
                          >
                            <Button>Remove</Button>
                          </Table.Cell>
                        </Table.Row>
                      );
                    })}
                </Table.Body>

                <Table.Footer></Table.Footer>
              </Table>
            </GridColumn>
            <GridColumn width={5}>
              <Table celled selectable sortable striped name="Blaclisted Table">
                <Table.Header>
                  <Table.Row>
                    <Table.HeaderCell>ID</Table.HeaderCell>
                    <Table.HeaderCell>Seller Name</Table.HeaderCell>
                    <Table.HeaderCell>Email</Table.HeaderCell>
                    <Table.HeaderCell>Remove</Table.HeaderCell>
                  </Table.Row>
                </Table.Header>
                <Table.Body>
                  {blackList &&
                    blackList.sellerBlackList &&
                    blackList.sellerBlackList.map((value, index) => {
                      return (
                        <Table.Row>
                          <Table.Cell>
                            <Icon name="barcode"></Icon>
                            {value.id}
                          </Table.Cell>
                          <Table.Cell>{value.name}</Table.Cell>
                          <Table.Cell>{value.email}</Table.Cell>
                          <Table.Cell
                            negative
                            a
                            lkey={value.id}
                            onClick={() => this.blackList(value.id)}
                          >
                            <Button>Remove</Button>
                          </Table.Cell>
                        </Table.Row>
                      );
                    })}
                </Table.Body>
                <Table.Footer></Table.Footer>
              </Table>
            </GridColumn>
          </Grid>
        </Segment>
      </Container>
    );
  }
}
export default AdminDashboard;
