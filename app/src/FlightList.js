import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';

const FlightList = () => {

  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);
  const [cookies] = useCookies(['XSRF-TOKEN']);

  useEffect(() => {
      setLoading(true);

      fetch('api/flights')
        .then(response => response.json())
        .then(data => {
          setGroups(data);
          setLoading(false);
        })
    }, []);

   const remove = async (id) => {
      await fetch(`/api/del/flight/${id}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      }).then(() => {
        let updatedGroups = [...groups].filter(i => i.id !== id);
        setGroups(updatedGroups);
      });
    }

    if (loading) {
       return <p>Loading...</p>;
     }

    const flightList = groups.map(group => {
        //const address = `${group.flightFrom || ''} ${group.flightTo || ''}`;
        return <tr key={group.id}>
          <td style={{whiteSpace: 'nowrap'}}>{group.flightName}</td>
          <td>{group.flightFrom}</td>
          <td>{group.flightTo}</td>
          <td>
            <div>{new Intl.DateTimeFormat('en-US', {
                              year: 'numeric',
                              month: 'long',
                              day: '2-digit'
                            }).format(new Date(group.flightDate))}</div>

          </td>
          <td>{group.flightCarrier}</td>
        </tr>
      });

   return (
       <div>
         <AppNavbar/>
         <Container fluid>
           <div className="float-end">
                   <Button color="success" tag={Link} to="/flights/new">Create Flight</Button>
           </div>
           <h3>My Flight Schedule</h3>
           <Table className="mt-4">
             <thead>
             <tr>
               <th width="20%">Flight Name</th>
               <th width="20%">From</th>
               <th width="20%">To</th>
               <th>Date</th>
               <th width="20%">Flight Carrier</th>
             </tr>
             </thead>
             <tbody>
             {flightList}
             </tbody>
           </Table>
         </Container>
       </div>
     );
   };

   export default FlightList;