import axios from 'axios';

export const sendNotifications = async () => {
  let res = await axios.get(
    'http://localhost:8080/api/v1/admin/notifications/send'
  );
  return res.data;
};
