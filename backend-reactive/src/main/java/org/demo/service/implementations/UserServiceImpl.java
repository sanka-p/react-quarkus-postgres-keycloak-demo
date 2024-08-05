//package org.demo.service.implementations;
//
//import io.smallrye.mutiny.Uni;
//import io.vertx.mutiny.core.eventbus.EventBus;
//import io.vertx.mutiny.core.eventbus.Message;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.demo.dto.UserDTO;
//import org.demo.response.Response;
//import org.demo.service.UserService;
//import org.jboss.resteasy.reactive.RestResponse;
//
//import java.util.List;
//
//@ApplicationScoped
//public class UserServiceImpl implements UserService {
//    @Inject
//    EventBus bus;
//
//    /**
//     * {@inheritDoc}
//     */
//    public Uni<Response<?>> getAllUsers(){
//        return bus.<List<UserDTO>>request("get-all-users", null)
//                .onItem().transform(Message::body)
//                .onItem().transform(userDTOs -> {
//                    Response<List<UserDTO>> response = new Response<>();
//                    response.setBody(userDTOs);
//                    response.setStatusCode(RestResponse.StatusCode.OK);
//                    return response;
//                });
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    public Uni<Response<?>> getUser(Long id){
//        return bus.<UserDTO>request("get-user", id)
//                .onItem().transform(Message::body)
//                .onItem().transform(userDTO -> {
//                    Response<UserDTO> response = new Response<>();
//                    response.setBody(userDTO);
//                    response.setStatusCode(RestResponse.StatusCode.OK);
//                    return response;
//                });
//    }
//}
