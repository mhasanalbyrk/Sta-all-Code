import React from "react";
import { Form, Button, Container, Grid, Segment } from "semantic-ui-react";
import { Table, Menu, Icon, Label } from "semantic-ui-react";
import { withRouter } from "react-router-dom";
import {
  getProductsUtil,
  MenuUtil,
  editProductUtil,
  removeFromProductsUtil,
} from "./util";

class AdminDashboardProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: {},
      currentPage: 0,
      newProductName: null,
      newProductCategory: null,
      newProductDescription: null,
      newProductStock: null,
      newProductPrice: null,
      productId: null,
      name: null,
      category: null,
      description: null,
    };
  }
  componentDidMount = () => {
    //this.getProducts();
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

  deleteProduct = (productId) => {
    removeFromProductsUtil(productId);
    window.location.reload();
  };

  editProduct = (event) => {
    const { productId } = this.state;
    event.preventDefault();
    const {
      newProductCategory,
      newProductDescription,
      newProductName,
      newProductPrice,
      newProductStock,
    } = this.state;
    editProductUtil(
      productId,
      newProductName,
      newProductPrice,
      newProductStock,
      newProductDescription,
      newProductCategory
    );
  };

  editUser = (productId) => {
    this.setState({
      productId: productId,
    });
    alert(productId);
    this.setState({ editing: true });
  };

  changePageTo = (i) => {
    //this.setState({ currentPage: i }, this.getSellers);
    //************** */
    const { name, description, category } = this.state;

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
    const { editing, products } = this.state;
    const pageArray = [...Array(products.totalPages).keys()];
    return (
      <Container>
        <MenuUtil></MenuUtil>

        <Grid>
          <Grid.Column width={4}>
            <Form onSubmit={this.componentDidMount}>
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
                  <Table.HeaderCell>Edit</Table.HeaderCell>
                  <Table.HeaderCell>Delete</Table.HeaderCell>
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
                          onClick={() => this.editUser(value.id)}
                        >
                          <Button>Edit</Button>
                        </Table.Cell>
                        <Table.Cell
                          negative
                          a
                          lkey={value.id}
                          onClick={() => this.deleteProduct(value.id)}
                        >
                          <Button>Delete</Button>
                        </Table.Cell>
                      </Table.Row>
                    );
                  })}
              </Table.Body>

              <Table.Footer>
                <Table.Row>
                  <Table.HeaderCell colSpan="10">
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
        <Segment disabled={!editing}>
          <Form
            onSubmit={this.editProduct}
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
                required
                name="newProductPrice"
                value={this.state.newProductPrice}
                onChange={this.handleChange}
              ></Form.Input>
            </Form.Field>
            <Form.Field>
              <label>newProductStock</label>
              <Form.Input
                type="number"
                required
                name="newProductStock"
                value={this.state.newProductStock}
                onChange={this.handleChange}
              ></Form.Input>
            </Form.Field>
            <Button type="submit">Edit Product</Button>
          </Form>
        </Segment>
      </Container>
    );
  }
}
export default AdminDashboardProduct;
