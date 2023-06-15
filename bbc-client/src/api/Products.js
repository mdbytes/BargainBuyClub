import axios from 'axios';

export const updateSystemPrices = async () => {
  let res = await axios.get(
    'http://localhost:8080/api/v1/admin/products/update'
  );
  return res.data;
};
