// src/store/index.js
import { createStore } from 'vuex';

const store = createStore({
  state: {
    accountNumber: null,
    isAuthenticated: false,
  },
  mutations: {
    setAccountNumber(state, accountNumber) {
      state.accountNumber = accountNumber;
    },
    setAuthenticated(state, isAuthenticated) {
      state.isAuthenticated = isAuthenticated;
    },
  },
  actions: {
    login({ commit }, { accountNumber }) {
      commit('setAccountNumber', accountNumber);
      commit('setAuthenticated', true);
    },
    logout({ commit }) {
      commit('setAccountNumber', null);
      commit('setAuthenticated', false);
    },
  },
});

export default store;
