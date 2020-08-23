package io.github.emusute1212.makasetechoice.members

sealed class MemberMessenger {
    object Init : MemberMessenger()
    object OnClickAddButton : MemberMessenger()
    object OnDoAddMember : MemberMessenger()
    object OnCancelAdd : MemberMessenger()
}