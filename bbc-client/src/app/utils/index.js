export const toCurrencyString = (n) => {
  return n.toLocaleString('en-GB', {
    style: 'currency',
    currency: 'GBP',
  });
};
