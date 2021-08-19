import fetch from "isomorphic-unfetch";
import { toast } from "react-toastify";
import { Link, withRouter } from "react-router-dom";
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

export function getProductsUtil(name, description, category, pageNumber) {
  const token = localStorage.getItem("token");
  return fetch(
    "http://localhost:8080/api/product/all?" +
      new URLSearchParams({
        pageNumber: pageNumber,
        name: name,
        category: category,
        description: description,
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
      //this.setState({ products: response });

      toast.info(` ${response.totalElements} fetched succesfully`);
      return response;
    });
  // .catch((e) => {
  //  toast.error(e.message);
  //  });
}
export function AddFavUtil(productId, action, userId) {
  const token = localStorage.getItem("token");

  alert(productId);

  fetch(
    "http://localhost:8080/api/user/favorite?" +
      new URLSearchParams({
        user: userId,
        product: productId,
        action: action,
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
      if (action === "add") {
        toast.info(`  ${response.message} `);
      } else {
        toast.info(`  ${response.message} `);
      }
      return response;
    })
    .catch((e) => {
      toast.error(e.message);
    });
}

export function blacListUtil(sellerId, action) {
  const userId = localStorage.getItem("id");
  const token = localStorage.getItem("token");

  fetch(
    "http://localhost:8080/api/user/blacklist?" +
      new URLSearchParams({
        user: userId,
        seller: sellerId,
        action: action,
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
      if (action === "add") {
        toast.info(` ${response.message} blacklisted`);
      } else {
        toast.info(`  ${response.message} removed `);
      }
    })
    .catch((e) => {
      toast.error(e.message);
    });
}

export function LinkUtil() {
  if (localStorage.getItem("role") === "ROLE_ADMIN") {
    return <Link to="/adminDashboard"> To dashboard</Link>;
  }
  if (localStorage.getItem("role") === "ROLE_USER") {
    return <Link to="/dashboard"> To dashboard</Link>;
  }
}

export function MenuUtil() {
  if (localStorage.getItem("role") === "ROLE_ADMIN") {
    return (
      <Menu>
        <Link to="/login">
          <Menu.Item>Login</Menu.Item>
        </Link>
        <Link to="/adminDashboard">
          <Menu.Item>Users</Menu.Item>
        </Link>
        <Link to="/adminDashboardSeller">
          <Menu.Item>Sellers</Menu.Item>
        </Link>
        <Link to="/adminDashboardProduct">
          <Menu.Item>Products</Menu.Item>
        </Link>
      </Menu>
    );
  }
  if (localStorage.getItem("role") === "ROLE_USER") {
    return (
      <Menu>
        <Link to="/login" disabled={true}>
          <Menu.Item disabled>Login</Menu.Item>
        </Link>
        <Link to="/dashboard">
          <Menu.Item>Market</Menu.Item>
        </Link>
      </Menu>
    );
  }
}

export function editProductUtil(
  productId,
  newProductName,
  newProductPrice,
  newProductStock,
  newProductDescription,
  newProductCategory
) {
  const token = localStorage.getItem("token");
  fetch(
    "http://localhost:8080/api/product/update?" +
      new URLSearchParams({
        productId: productId,
        name: newProductName,
        category: newProductCategory,
        description: newProductDescription,
        stock: newProductStock,
        price: newProductPrice,
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
      toast.info(`Product with id  ${response.id} updated`);
    })
    .catch((e) => {
      toast.error(e.message);
    });
}

export function removeFromProductsUtil(productId) {
  const token = localStorage.getItem("token");
  fetch(
    "http://localhost:8080/api/product/delete?" +
      new URLSearchParams({
        id: productId,
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
      toast.info(`${response.id} deleted succesfully`);
    })
    .catch((e) => {
      toast.error(e.message);
    });
}

export function getUsersUtil(currentPage, name, email) {
  const token = localStorage.getItem("token");
  fetch(
    "http://localhost:8080/api/user/all?" +
      new URLSearchParams({
        pageNumber: currentPage,
        name: name,
        email: email,
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
      //this.setState({ users: response });
      toast.info(`${response.totalElements} fetched succesfully`);
      return response;
    });
  /* .catch((e) => {
      toast.error(e.message);
    });*/
}
