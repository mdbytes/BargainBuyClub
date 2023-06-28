import axios from 'axios';

export const updateSystemPrices = async () => {
  let res = await axios.get(
    'https://bbc-server.mdbytes.us/api/v1/admin/products/update'
  );
  return res.data;
};
