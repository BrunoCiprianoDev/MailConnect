import { React, useEffect } from 'react'

import { Table, Card, Alert } from 'react-bootstrap';
import { useFetch } from '../hooks/useFetch';

import Loading from '../components/Loading';

const RequestEmailIMAP = () => {


  const { data: emails, getData, loading, errorMessage } = useFetch("/IMAP/inbox");

  useEffect(() => {
    getData() // eslint-disable-next-line
  }, []);


  return (
    <>
      {loading && <Loading />}
      {errorMessage && <Alert className="text-center" variant={"danger"}>{errorMessage}</Alert>}
      <Card className='shadow-md'>
        <Card.Body>
          <div style={{ minHeight: "72vh", overflowX: "scroll" }}>
            <Table className="text-center bg-white">
              <thead>
                <tr>
                  <th>Email from</th>
                  <th>Email to</th>
                  <th>Subject</th>
                </tr>
              </thead>
              <tbody>
                {emails && emails.map((email, index) => (
                  <tr key={index} >
                    <td>{email.emailFrom}</td>
                    <td>{email.emailTo}</td>
                    <td>{email.subject}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>
        </Card.Body>
      </Card>
    </>
  )
}

export default RequestEmailIMAP