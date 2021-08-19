import React from "react";
import { toast } from "react-toastify";
import fetch from "isomorphic-unfetch";
import { Table, Menu, Container, Grid, Icon, Label } from "semantic-ui-react";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      books: {},
      currentPage: 0,
    };
  }
  componentDidMount = () => {
    this.getBooks();
  };

  getBooks = () => {
    const token = window.localStorage.getItem("token");
    fetch(
      "http://localhost:8081/api/books?" +
        new URLSearchParams({ pageNumber: this.state.currentPage }),
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
        this.setState({ books: response });
        toast.info(`${response.totalElements} fetched succesfully`);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  changePageTo = (i) => {
    this.setState({ currentPage: i }, this.getBooks);
  };

  render() {
    const { books } = this.state;
    const pageArray = [...Array(books.totalPages).keys()];
    return (
      <Container>
        <Grid>
          <Grid.Column>
            <Table celled>
              <Table.Header>
                <Table.Row>
                  <Table.HeaderCell>Index</Table.HeaderCell>
                  <Table.HeaderCell>Name</Table.HeaderCell>
                  <Table.HeaderCell>ISBN</Table.HeaderCell>
                  <Table.HeaderCell>Publisher</Table.HeaderCell>
                </Table.Row>
              </Table.Header>

              <Table.Body>
                {books &&
                  books.content &&
                  books.content.map((value, index) => {
                    return (
                      <Table.Row>
                        <Table.Cell>
                          <Label ribbon>
                            {books.size * books.number + (index + 1)}
                          </Label>
                        </Table.Cell>
                        <Table.Cell>{value.name}</Table.Cell>
                        <Table.Cell>
                          <Icon name="barcode"></Icon>
                          {value.isbn}
                        </Table.Cell>
                        <Table.Cell>{value.publisher}</Table.Cell>
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
                        disabled={books.first}
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
                            active={books.number === value}
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
                        disabled={books.last}
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
