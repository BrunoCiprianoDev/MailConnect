import { BrowserRouter, Routes, Route } from 'react-router-dom';

//Components
import WriteEmail from './pages/WriteEmail';
import RequestEmailPOP3 from './pages/RequestEmailPOP3';
import RequestEmailIMAP from './pages/RequestEmailIMAP';
import Header from './components/Header';
import Sidebar from './components/Sidebar';

//Styles
import './styles/App.module.css';
import 'bootstrap/dist/css/bootstrap.css';
import styles from './styles/App.module.css';

function App() {
  return (
    <div className={styles['layout']}>
      <BrowserRouter>
        <Header/>
        <Sidebar/>
        <Routes>
          <Route path="/form/email" element={<WriteEmail />} />
          <Route path="/request/pop3" element={<RequestEmailPOP3 />} />
          <Route path="/request/imap" element={<RequestEmailIMAP />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
