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
import { MenuUtil, removeFromProductsUtil } from "./util";

class AdminDashboardSeller extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      sellers: {},
      currentPage: 0,
      productList: [],
      favName: null,
      newUserName: null,
      newEmail: null,
      editUserName: null,
      editEmail: null,
      name: null,
      email: null,
      editing: false,
      newProductName: null,
      newProductCategory: null,
      newProductDescription: null,
      newProductStock: null,
      newProductPrice: null,
    };
  }
  componentDidMount = () => {
    this.getSellers();
  };

  addSeller = (event) => {
    event.preventDefault();
    const { newUserName, newEmail } = this.state;
    const token = localStorage.getItem("token");

    fetch(
      "http://localhost:8080/api/seller/add?" +
        new URLSearchParams({
          username: newUserName,
          email: newEmail,
        }),

      {
        method: "POST",
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
        toast.info(`Seller with id  ${response.id} created `);
      })
      .catch((e) => {
        toast.error(e.message);
      });
    this.setState({ newUserName, newEmail });
  };

  addProduct = (event) => {
    event.preventDefault();
    const {
      sellerId,
      newProductCategory,
      newProductDescription,
      newProductName,
      newProductPrice,
      newProductStock,
    } = this.state;
    const token = localStorage.getItem("token");

    fetch(
      "http://localhost:8080/api/product/create?" +
        new URLSearchParams({
          sellerId: sellerId,
          name: newProductName,
          category: newProductCategory,
          description: newProductDescription,
          stock: newProductStock,
          price: newProductPrice,
        }),

      {
        method: "POST",
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
        toast.info(`Product with id  ${response.id} created`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  editUser = (sellerId) => {
    this.setState({
      sellerId: sellerId,
    });
    alert(sellerId);
    const { sellers } = this.state;
    this.setState({ editing: true });
    sellers.content.forEach((element) => {
      if (element.id === sellerId) {
        this.setState({
          productList: element.productList,
        });
      }
    });
  };

  getSellers = () => {
    const token = localStorage.getItem("token");
    const { name, email } = this.state;
    fetch(
      "http://localhost:8080/api/seller/all?" +
        new URLSearchParams({
          pageNumber: this.state.currentPage,
          name: name,
          email: email,
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
        this.setState({ sellers: response });
        toast.info(`${response.totalElements} fetched succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
    this.setState({ name: null });
    this.setState({ email: null });
  };
  editSeller = (event) => {
    alert("Edit  seller");

    event.preventDefault();
    const token = localStorage.getItem("token");
    const { sellerId, editUserName, editEmail } = this.state;
    fetch(
      "http://localhost:8080/api/seller/update?" +
        new URLSearchParams({
          sellerId: sellerId,
          username: editUserName,
          email: editEmail,
          //description: this.state.description,
        }),

      {
        method: "PUT",
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
        toast.info(`Seller with id ${response.id} edited succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
    this.setState({ editUserName: "" });
    this.setState({ editEmail: "" });
  };

  deleteSeller = (sellerId) => {
    alert("Remove product");
    const token = localStorage.getItem("token");

    fetch(
      "http://localhost:8080/api/seller/delete?" +
        new URLSearchParams({
          id: sellerId,
          //description: this.state.description,
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
        toast.info(` Seller with id ${response.id} deleted succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  removeFromProducts = (productId) => {
    removeFromProductsUtil(productId);
    window.location.reload();
    this.componentDidMount();
    this.editUser(this.state.sellerId);
  };

  changePageTo = (i) => {
    this.setState({ currentPage: i }, this.getSellers);
  };
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  redirect(to) {
    this.props.history.push({
      pathname: to,
    });
  }
  render() {
    const { sellers } = this.state;
    const { productList } = this.state;
    const { editing } = this.state;
    const pageArray = [...Array(sellers.totalPages).keys()];
    return (
      <Container>
        <MenuUtil></MenuUtil>

        <Form onSubmit={this.addSeller}>
          <Form.Group>
            <Form.Field>
              <label>Seller Name</label>
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

            <Button type="submit">Add New Seller</Button>
          </Form.Group>
        </Form>
        <Divider></Divider>
        <Grid>
          <Grid.Column width={4}>
            <Form onSubmit={this.getSellers} name="searchForm">
              <Form.Field>
                <label>Seller Name</label>
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
            <Table celled selectable sortable striped name="sellerTable">
              <Table.Header>
                <Table.Row>
                  <Table.HeaderCell>Number</Table.HeaderCell>
                  <Table.HeaderCell>ID</Table.HeaderCell>
                  <Table.HeaderCell>Seller Name</Table.HeaderCell>
                  <Table.HeaderCell>Email</Table.HeaderCell>
                  <Table.HeaderCell>Edit</Table.HeaderCell>
                  <Table.HeaderCell>Delete</Table.HeaderCell>
                </Table.Row>
              </Table.Header>
              <Table.Body>
                {sellers &&
                  sellers.content &&
                  sellers.content.map((value, index) => {
                    return (
                      <Table.Row>
                        <Table.Cell>
                          <Label ribbon>
                            {sellers.size * sellers.number + (index + 1)}
                          </Label>
                        </Table.Cell>
                        <Table.Cell>{value.id}</Table.Cell>
                        <Table.Cell>{value.username}</Table.Cell>
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
                          onClick={() => this.deleteSeller(value.id)}
                        >
                          <Button>Delete</Button>
                        </Table.Cell>
                      </Table.Row>
                    );
                  })}
              </Table.Body>

              <Table.Footer>
                <Table.Row>
                  <Table.HeaderCell colSpan="6">
                    <Menu floated="right" pagination>
                      <Menu.Item
                        onClick={() => {
                          this.changePageTo(this.state.currentPage - 1);
                        }}
                        as="a"
                        disabled={sellers.first}
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
                            active={sellers.number === value}
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
                        disabled={sellers.totalElements < 10}
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
          <Grid columns={3}>
            <GridColumn>
              <Form
                onSubmit={this.editSeller}
                onScreen={editing}
                name="Edit seller"
              >
                <Form.Field>
                  <label>New Seller Name</label>
                  <Form.Input
                    type="text"
                    required
                    name="editUserName"
                    value={this.state.editUserName}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>New email</label>
                  <Form.Input
                    type="email"
                    required
                    name="editEmail"
                    value={this.state.editEmail}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>

                <Button type="submit">Edit</Button>
              </Form>
            </GridColumn>
            <GridColumn>
              <Table celled selectable sortable striped name="Products table">
                <Table.Header>
                  <Table.Row>
                    <Table.HeaderCell>Number</Table.HeaderCell>
                    <Table.HeaderCell>ID</Table.HeaderCell>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Category</Table.HeaderCell>
                  </Table.Row>
                </Table.Header>
                <Table.Body>
                  {productList &&
                    productList &&
                    productList.map((value, index) => {
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
                            onClick={() => this.removeFromProducts(value.id)}
                          >
                            <Button>Delete</Button>
                          </Table.Cell>
                        </Table.Row>
                      );
                    })}
                </Table.Body>
              </Table>
            </GridColumn>
            <GridColumn>
              <Form
                onSubmit={this.addProduct}
                onScreen={editing}
                name="newProductForm"
              >
                <Form.Field>
                  <label>New Product Name</label>
                  <Form.Input
                    type="text"
                    required
                    name="newProductName"
                    value={this.state.newProductName}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>newProductCategory</label>
                  <Form.Input
                    type="text"
                    required
                    name="newProductCategory"
                    value={this.state.newProductCategory}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>newProductDescription</label>
                  <Form.Input
                    type="text"
                    required
                    name="newProductDescription"
                    value={this.state.newProductDescription}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>newProductPrice</label>
                  <Form.Input
                    type="number"
                    name="newProductPrice"
                    value={this.state.newProductPrice}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Form.Field>
                  <label>newProductStock</label>
                  <Form.Input
                    required
                    type="number"
                    name="newProductStock"
                    value={this.state.newProductStock}
                    onChange={this.handleChange}
                  ></Form.Input>
                </Form.Field>
                <Button type="submit">Add New Product</Button>
              </Form>
            </GridColumn>
          </Grid>
        </Segment>
      </Container>
    );
  }
}
export default withRouter(AdminDashboardSeller);
