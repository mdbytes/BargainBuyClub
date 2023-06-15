export const toCurrencyString = (n) => {
  return n.toLocaleString('en-US', {
    style: 'currency',
    currency: 'USD',
  });
};
