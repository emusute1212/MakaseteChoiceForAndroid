package io.github.emusute1212.makasetechoice.groups

sealed class GroupMessenger {
    object Init : GroupMessenger()
    object OnClickChoiceButton : GroupMessenger()
    object OnDoChoiceGroup : GroupMessenger()
    object OnCancelChoice : GroupMessenger()
}