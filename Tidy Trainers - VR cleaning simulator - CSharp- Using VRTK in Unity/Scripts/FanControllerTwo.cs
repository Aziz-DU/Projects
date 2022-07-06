/// <summary>
/// Aziz
/// script that changes fan speed and turns it off after 3 pulls & connects springs to held object
/// </summary>
namespace VRTK.Examples
{
    using UnityEngine;
    namespace WestgateVRTS
    {
        public class FanControllerTwo : MonoBehaviour
        {
            private VRTK_ControllerReference controllerReference;
            public VRTK_InteractableObject linkedObject;
            public Transform cordBase;
            protected Rigidbody[] lampRigidbodies = new Rigidbody[0];
            bool fanTick = false;
            [SerializeField] GameObject accessFan;
            float fastenFan = 70f;
            Vector3 fanRotAxis = Vector3.up;
            [SerializeField] FanController otherHandle; // just to use the same rot speed variable(important to have fan turn off after 3 pulls)

            private void Start()
            {
                float distance = (transform.position - cordBase.transform.position).magnitude;
            }
            private void Update()
            {
                accessFan.transform.Rotate(fanRotAxis, otherHandle.fanRotSpeed * Time.deltaTime);

                if (otherHandle.fanRotSpeed >= 220f)
                {
                    otherHandle.fanRotSpeed = 0f;
                }

                float distance = (transform.position - cordBase.transform.position).magnitude;
                if (distance > 0.1f)
                {
                    fanTick = true;
                }
                else
                {
                    fanTick = false;
                }
            }

            protected virtual void OnEnable()
            {
                linkedObject = (linkedObject == null ? GetComponent<VRTK_InteractableObject>() : linkedObject);

                if (linkedObject != null)
                {
                    linkedObject.InteractableObjectGrabbed += InteractableObjectGrabbed;
                    linkedObject.InteractableObjectUngrabbed += InteractableObjectUngrabbed;
                }

                lampRigidbodies = transform.parent.GetComponentsInChildren<Rigidbody>();
            }

            protected virtual void OnDisable()
            {
                if (linkedObject != null)
                {
                    linkedObject.InteractableObjectGrabbed -= InteractableObjectGrabbed;
                    linkedObject.InteractableObjectUngrabbed -= InteractableObjectUngrabbed;
                }
            }

            protected virtual void InteractableObjectUngrabbed(object sender, InteractableObjectEventArgs e)
            {
                ToggleKinematics(false);

                if (fanTick)
                {
                    otherHandle.fanRotSpeed = otherHandle.fanRotSpeed + fastenFan; //held, pulled and ungrabbed
                    transform.rotation = Quaternion.identity;

                }
            }

            protected virtual void InteractableObjectGrabbed(object sender, InteractableObjectEventArgs e)
            {
                ToggleKinematics(false);
                /*
                if (fanTick)
                {
                    Debug.Log("maybe an indicator (haptic?) to let user now it's been ticked");
                }
                */
            }
            protected virtual void ToggleKinematics(bool state)
            {
                foreach (Rigidbody elementRigidbody in lampRigidbodies)
                {
                    elementRigidbody.isKinematic = state;

                }
            }
        }
    }
}