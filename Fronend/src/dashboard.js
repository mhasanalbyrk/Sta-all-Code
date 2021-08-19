import React from "react";

import { Link, withRouter } from "react-router-dom";
import { AddFavUtil, blacListUtil, getProductsUtil, MenuUtil } from "./util";

import {
  Table,
  Menu,
  Container,
  Grid,
  Icon,
  Label,
  Form,
  Button,
} from "semantic-ui-react";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: {},
      currentPage: 0,
      name: null,
      category: null,
      description: null,
    };
  }
  componentDidMount = () => {
    const { name, description, category, currentPage } = this.state;
    //this.getProducts();
    getProductsUtil(name, description, category, currentPage).then(
      (response) => {
        this.setState({
          products: response,
        });
      }
    );
  };

  blacList = (sellerId, action) => {
    /*const userId = localStorage.getItem("id");
    const token = localStorage.getItem("token");*/

    blacListUtil(sellerId, action);
    return;
  };

  AddFav = (productId, action) => {
    AddFavUtil(productId, action, localStorage.getItem("id"));
    return;
  };

  changePageTo = (i) => {
    //this.state.products = getProductsUtil(name, description, category, i);
    const { name, description, category } = this.state;
    //this.getProducts();
    getProductsUtil(name, description, category, i).then((response) => {
      this.setState({
        products: response,
      });
    });
    this.setState({
      currentPage: i,
    });
    this.setState({ name: null });
    this.setState({ category: null });
    this.setState({ description: null });
  };
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  render() {
    const { products } = this.state;
    const pageArray = [...Array(products.totalPages).keys()];
    return (
      <Container>
        <MenuUtil></MenuUtil>
        <Grid>
          <Grid.Column width={2}>
            <Form onSubmit={this.getProducts}>
              <Form.Field>
                <label>Name</label>
                <Form.Input
                  type="text"
                  name="name"
                  value={this.state.name}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Category</label>
                <Form.Input
                  type="text"
                  name="category"
                  value={this.state.category}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Description</label>
                <Form.Input
                  type="text"
                  name="description"
                  value={this.state.description}
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
                  <Table.HeaderCell>Name</Table.HeaderCell>
                  <Table.HeaderCell>Category</Table.HeaderCell>
                  <Table.HeaderCell>Description</Table.HeaderCell>
                  <Table.HeaderCell>Stock</Table.HeaderCell>
                  <Table.HeaderCell>Price</Table.HeaderCell>

                  <Table.HeaderCell>Seller</Table.HeaderCell>
                  <Table.HeaderCell>AddFavorite</Table.HeaderCell>
                  <Table.HeaderCell>RemoveFavorite</Table.HeaderCell>
                  <Table.HeaderCell>BlackList</Table.HeaderCell>
                  <Table.HeaderCell>Remove BlackList</Table.HeaderCell>
                </Table.Row>
              </Table.Header>

              <Table.Body>
                {products &&
                  products.content &&
                  products.content.map((value, index) => {
                    return (
                      <Table.Row>
                        <Table.Cell>
                          <Label ribbon>
                            {products.size * products.number + (index + 1)}
                          </Label>
                        </Table.Cell>
                        <Table.Cell>
                          <Icon name="barcode"></Icon>
                          {value.id}
                        </Table.Cell>
                        <Table.Cell>{value.name}</Table.Cell>
                        <Table.Cell>{value.category}</Table.Cell>
                        <Table.Cell>{value.description}</Table.Cell>
                        <Table.Cell>{value.inStock}</Table.Cell>
                        <Table.Cell>{value.price}$</Table.Cell>

                        <Table.Cell>{value.sellerName}</Table.Cell>
                        <Table.Cell
                          positive
                          a
                          lkey={value.id}
                          onClick={() => this.AddFav(value.id, "add")}
                        >
                          <Button>AddFavorite</Button>
                        </Table.Cell>
                        <Table.Cell
                          negative
                          a
                          lkey={value.id}
                          onClick={() => this.AddFav(value.id, "remove")}
                        >
                          <Button>RemoveFavorite</Button>
                        </Table.Cell>
                        <Table.Cell
                          negative
                          a
                          lkey={value.sellerId}
                          onClick={() => this.blacList(value.sellerId, "add")}
                        >
                          <Button>Blaclist</Button>
                        </Table.Cell>
                        <Table.Cell
                          positive
                          a
                          lkey={value.sellerId}
                          onClick={() =>
                            this.blacList(value.sellerId, "remove")
                          }
                        >
                          <Button>Remove Blaclist</Button>
                        </Table.Cell>
                      </Table.Row>
                    );
                  })}
              </Table.Body>

              <Table.Footer>
                <Table.Row>
                  <Table.HeaderCell colSpan="13">
                    <Menu floated="right" pagination>
                      <Menu.Item
                        onClick={() => {
                          this.changePageTo(this.state.currentPage - 1);
                        }}
                        as="a"
                        disabled={this.state.currentPage === 0}
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
                            active={products.number === value}
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
                        disabled={products.totalElements < 10}
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
      </Container>
    );
  }
}
export default Dashboard;
