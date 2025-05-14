import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Register from './pages/Register';
import Login from './pages/Login';
import MainLayout from './layouts/MainLayout';

function App() {
 return (
  
  <Routes>
    <Route path='/' element={<MainLayout />} >
    <Route index element={<Home /> } />
    <Route path='/register' element={<Register/>} /> 
    <Route path='/login' element={<Login/>}/>
</Route>
  </Routes>

 
 );
};

export default App;
