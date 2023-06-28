import axios from 'axios';

export const sendNotifications = async () => {
  let res = await axios.get(
    'https://bbc-server.mdbytes.us/api/v1/admin/notifications/send'
  );
  return res.data;
};
