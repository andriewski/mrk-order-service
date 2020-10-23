package by.mark.orderservice.mapper;

import by.mark.orderservice.dto.UserDto;
import by.mark.orderservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}