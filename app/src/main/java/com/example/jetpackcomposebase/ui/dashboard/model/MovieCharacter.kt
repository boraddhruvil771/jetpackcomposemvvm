package com.example.jetpackcomposebase.ui.dashboard.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieCharacter(
    @JsonProperty("actor")
    val actor: String,

    @JsonProperty("alive")
    val alive: Boolean,

    @JsonProperty("alternate_actors")
    val alternateActors: List<String>,

    @JsonProperty("alternate_names")
    val alternateNames: List<String>,

    @JsonProperty("ancestry")
    val ancestry: String,

    @JsonProperty("eyeColour")
    val eyeColour: String,

    @JsonProperty("gender")
    val gender: String,

    @JsonProperty("hairColour")
    val hairColour: String,

    @JsonProperty("hogwartsStaff")
    val hogwartsStaff: Boolean,

    @JsonProperty("hogwartsStudent")
    val hogwartsStudent: Boolean,

    @JsonProperty("house")
    val house: String,

    @JsonProperty("image")
    val image: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("patronus")
    val patronus: String,

    @JsonProperty("species")
    val species: String,

    @JsonProperty("wand")
    val wand: Wand,

    @JsonProperty("wizard")
    val wizard: Boolean,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Wand(
    @JsonProperty("wood")
    val wood: String,

    @JsonProperty("core")
    val core: String,
)
