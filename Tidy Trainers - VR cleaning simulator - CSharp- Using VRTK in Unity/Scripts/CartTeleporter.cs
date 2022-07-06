/// <summary>
/// Aziz
/// Script that teleports you to the cart and back to your previous location
/// </summary>

namespace VRTK.Examples
{
    using UnityEngine;
    namespace WestgateVRTS
    {
        public class CartTeleporter : VRTK_DestinationMarker
        {
            public Transform destination;
            public Transform oldPos;
            public bool returnToOldPos = false;
            private bool lastUsePressedState = false;
       
            public void OnTriggerStay(Collider collider)
            {
                VRTK_ControllerEvents controller = (collider.GetComponent<VRTK_ControllerEvents>() ? collider.GetComponent<VRTK_ControllerEvents>() : collider.GetComponentInParent<VRTK_ControllerEvents>());
                if (controller != null)
                {
                    if (lastUsePressedState == true && !controller.triggerPressed)
                    {
                        if (returnToOldPos)
                        {
                            float distance = Vector3.Distance(transform.position, oldPos.position);
                            VRTK_ControllerReference controllerReference = VRTK_ControllerReference.GetControllerReference(controller.gameObject);
                            OnDestinationMarkerSet(SetDestinationMarkerEvent(distance, oldPos, new RaycastHit(), oldPos.position, controllerReference));
                            oldPos.transform.parent = this.transform;
                            oldPos.transform.localPosition = new Vector3(0,0,0);
                            returnToOldPos = false;
                        }
                        else
                        {
                            oldPos.parent = null;
                            float distance = Vector3.Distance(transform.position, destination.position);
                            VRTK_ControllerReference controllerReference = VRTK_ControllerReference.GetControllerReference(controller.gameObject);
                            OnDestinationMarkerSet(SetDestinationMarkerEvent(distance, destination, new RaycastHit(), destination.position, controllerReference));
                            returnToOldPos = true;
                        }
                    }
                    lastUsePressedState = controller.triggerPressed;
                }
            }        
        }
    }
}