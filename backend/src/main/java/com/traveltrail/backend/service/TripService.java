package com.traveltrail.backend.service;

import com.traveltrail.backend.dto.TripDto;
import com.traveltrail.backend.model.Trip;
import com.traveltrail.backend.model.User;
import com.traveltrail.backend.repository.TripRepository;
import com.traveltrail.backend.repository.UserRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public TripDto createTrip(TripDto tripDto) {
//        // Get the authenticated user's details
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String currentUsername = userDetails.getUsername();
//
//        // Fetch the user entity based on authenticated username
//        User authenticatedUser = userRepository.findByEmail(currentUsername)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//
//        // Ensure the user ID in the request matches the authenticated user
//        if (authenticatedUser.getId() != (tripDto.getUserId())) {
//            throw new SecurityException("User ID does not match the authenticated user");
//        }

        // Fetch the user entity
        User user = userRepository.findById(tripDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // Build the Trip entity from the DTO
        Trip newTrip = Trip.builder()
                .name(tripDto.getName())
                .destination(tripDto.getDestination())
                .startDate(tripDto.getStartDate())
                .endDate(tripDto.getEndDate())
                .coverPhotoUrl(tripDto.getCoverPhotoUrl())
                .user(user) // Set the user
                .build();

        // Save the trip
        Trip savedTrip = tripRepository.save(newTrip);

        // Convert the saved entity back to DTO
        TripDto savedTripDto = TripDto.builder()
                .name(savedTrip.getName())
                .destination(savedTrip.getDestination())
                .startDate(savedTrip.getStartDate())
                .endDate(savedTrip.getEndDate())
                .coverPhotoUrl(savedTrip.getCoverPhotoUrl())
                .userId(savedTrip.getUser().getId())
                .build();

        return savedTripDto;
    }

    public List<TripDto> findTripsByUserid(Long userId) {
        List<Trip> trips = tripRepository.findByUserId(userId);

        return trips.stream().map(trip -> modelMapper.map(trip,TripDto.class)).toList();
    }


}
