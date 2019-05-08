const proxy = [
  {
    context: '/api',
    target: 'http://localhost:8087/tcc-fatec-ws'
  }
];
module.exports = proxy;
