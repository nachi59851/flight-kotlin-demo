import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const FlightCreate = () => {
  const initialFormState = {
    flightName: '',
    flightFrom: '',
    flightTo: '',
    flightDate: '',
    flightCarrier: ''
  };
  const [group, setGroup] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

   useEffect(() => {
      if (id !== 'new') {
        fetch(`/api/flight/${id}`)
          .then(response => response.json())
          .then(data => setGroup(data));
      }
    }, [id, setGroup]);