import axios from 'axios';

export const addAlert = async (e) => {
  let storeId = parseInt(e.target[0].value);
  let productUrl = e.target[1].value;
  let alertPrice = parseFloat(e.target[2].value);
  let userId = localStorage.getItem('userId');
  let productId = await getProductId(productUrl, storeId);
  try {
    let res = await axios.post(
      'http://localhost:8080/api/v1/admin/alerts/add',
      {
        productId: productId,
        userId: userId,
        alertPrice: alertPrice,
      }
    );
    return res.status;
  } catch (err) {
    console.log(err);
    return 500;
  }
};

export const deleteAlert = async (e, alertId) => {
  console.log('alert', alertId);
  try {
    let requestUrl = 'http://localhost:8080/api/v1/alerts/' + alertId;
    let res = await axios.delete(requestUrl);
    return res;
  } catch (err) {
    console.log(err);
    let res = { status: 500 };
    return res;
  }
};

const getProductId = async (productUrl, storeId) => {
  try {
    let res = await axios.post(
      'http://localhost:8080/api/v1/admin/products/url',
      {
        productUrl: productUrl,
      }
    );
    if (res.data.productId && res.data.productId > 0) {
      return res.data.productId;
    } else {
      let res2 = await axios.post(
        'http://localhost:8080/api/v1/admin/products/add',
        {
          productUrl: productUrl,
          storeId: storeId,
        }
      );
      return res2.data.productId;
    }
  } catch (err) {
    console.log(err);
  }
};

export const getHomeAlerts = async () => {
  let alerts = [];
  try {
    let res = await axios.get('http://localhost:8080/api/v1/users/1/alerts');
    let rawAlerts = res.data._embedded.alerts;
    for (let rawAlert of rawAlerts) {
      let alert = {
        alertId: 0,
        alertPrice: 0.0,
        user: {},
        product: {},
        store: {},
      };

      alert.alertId = rawAlert.alertId;

      alert.alertPrice = rawAlert.alertPrice;

      let fetchUser = await axios.get(rawAlert._links.user.href);
      alert.user = fetchUser.data;

      let fetchProduct = await axios.get(rawAlert._links.product.href);
      alert.product = fetchProduct.data;

      let fetchStore = await axios.get(alert.product._links.store.href);
      alert.store = fetchStore.data;

      alerts.push(alert);
    }
  } catch (err) {
    console.log(err);
  }

  return alerts;
};

export const getAlertsByUserId = async (id) => {
  let alerts = [];
  try {
    let requestUrl = 'http://localhost:8080/api/v1/users/' + id + '/alerts';
    let res = await axios.get(requestUrl);
    let rawAlerts = res.data._embedded.alerts;
    for (let rawAlert of rawAlerts) {
      let alert = {
        alertId: 0,
        alertPrice: 0.0,
        user: {},
        product: {},
        store: {},
      };

      alert.alertId = rawAlert.alertId;

      alert.alertPrice = rawAlert.alertPrice;

      let fetchUser = await axios.get(rawAlert._links.user.href);
      alert.user = fetchUser.data;

      let fetchProduct = await axios.get(rawAlert._links.product.href);
      alert.product = fetchProduct.data;

      let fetchStore = await axios.get(alert.product._links.store.href);
      alert.store = fetchStore.data;

      alerts.push(alert);
    }
  } catch (err) {
    console.log(err);
  }
  return alerts;
};

export const getAllAlerts = async () => {
  let alerts = [];
  try {
    let requestUrl = 'http://localhost:8080/api/v1/alerts';
    let res = await axios.get(requestUrl);
    let rawAlerts = res.data._embedded.alerts;
    for (let rawAlert of rawAlerts) {
      let alert = {
        alertId: 0,
        alertPrice: 0.0,
        user: {},
        product: {},
        store: {},
      };

      alert.alertId = rawAlert.alertId;

      alert.alertPrice = rawAlert.alertPrice;

      let fetchUser = await axios.get(rawAlert._links.user.href);
      alert.user = fetchUser.data;

      let fetchProduct = await axios.get(rawAlert._links.product.href);
      alert.product = fetchProduct.data;

      let fetchStore = await axios.get(alert.product._links.store.href);
      alert.store = fetchStore.data;

      alerts.push(alert);
    }
  } catch (err) {
    console.log(err);
  }

  return alerts;
};

export const getAlertById = async (id) => {
  try {
    let requestUrl = 'http://localhost:8080/api/v1/alerts/' + id;
    let res = await axios.get(requestUrl);
    let rawAlert = res.data;
    let alert = {
      alertId: 0,
      alertPrice: 0.0,
      user: {},
      product: {},
      store: {},
    };

    alert.alertId = rawAlert.alertId;

    alert.alertPrice = rawAlert.alertPrice;

    let fetchUser = await axios.get(rawAlert._links.user.href);
    alert.user = fetchUser.data;

    let fetchProduct = await axios.get(rawAlert._links.product.href);
    alert.product = fetchProduct.data;

    let fetchStore = await axios.get(alert.product._links.store.href);
    alert.store = fetchStore.data;

    return alert;
  } catch (err) {
    console.log(err);

    return null;
  }
};
