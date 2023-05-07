import { React } from 'react'

import styles from '../styles/Sidebar.module.css'

import { NavLink } from 'react-router-dom'

import iconeSMTP from '../assets/icon-smpt.png'
import iconeIMAP from '../assets/icon-imap.png'
import iconPOP3 from '../assets/icon-pop3.png'

const Sidebar = () => {

   return (
      <div className={styles['MainContainer']}>
         <nav className={styles['NavContainer']}>
            <NavLink className={styles.NavLink} to={'/form/email'}>
               <img src={iconeSMTP} alt={'home'} />
               <p>Escrever e-mail</p>
            </NavLink>
            <NavLink className={styles.NavLink} to={'/request/pop3'}>
               <img src={iconPOP3} alt={'home'} />
               <p>E-mail POP3</p>
            </NavLink>
            <NavLink className={styles.NavLink} to={'/request/imap'}>
               <img src={iconeIMAP} alt={'home'} />
               <p>E-mail IMAP</p>
            </NavLink>
         </nav>
      </div>
   )
}

export default Sidebar