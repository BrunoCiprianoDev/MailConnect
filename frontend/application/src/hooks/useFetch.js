import { useState } from "react";

import { urlFetch } from "../ApiConfig";

export const useFetch = (url) => {

    const [data, setData] = useState(null);
    const [httpResponse, setHttpResponse] = useState('');
    const [responseMessage, setResponseMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);

    const getData = async () => {

        setLoading(true);
        setErrorMessage('');
        setHttpResponse('');
        setResponseMessage('');

        try {
            const response = await fetch(`${urlFetch}${url}`);

            setHttpResponse(response);

            if (!response.ok) {
                setLoading(false)
                return;
            }
            const json = await response.json();
            setData(json);

        } catch (error) {
            setErrorMessage(error.message);
        }

        setLoading(false);
    }

    const postData = async (data) => {

        setLoading(true);
        setErrorMessage('');
        setHttpResponse('');
        setResponseMessage('');
      
        try {
            const response = await fetch(`${urlFetch}${url}`, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
              });
      
          setHttpResponse(response);
          
        } catch (error) {
          setErrorMessage(error.status); // Alteração aqui
        }
      
        setLoading(false);
      }

    return {
        data,
        getData,
        postData,
        loading,
        httpResponse,
        errorMessage,
        responseMessage
    }

}