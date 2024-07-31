import ReactDOM from 'react-dom/client'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter } from "react-router-dom"
import { AuthProvider } from './context/AuthProvider.tsx';
import Router from "./Router"

ReactDOM.createRoot(document.getElementById('root')!).render(
  // <React.StrictMode>
      <AuthProvider>
        <BrowserRouter>
          <Router />
        </BrowserRouter>
      </AuthProvider>
  // </React.StrictMode>,
)
