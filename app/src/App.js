import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import FlightList from './FlightList';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path='/' exact={true} element={<FlightList/>}/>
      </Routes>
    </Router>
  )
}

export default App;
